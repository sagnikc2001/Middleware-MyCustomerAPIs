package com.alahli.middleware.customer.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.language.simple.Simple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.internal.OracleCallableStatement;

@Lazy
@Component
public class BANCSDBConnectorImplDao {

	@Autowired
	private DataSource dataSource;

	/**
	 * Executes a stored procedure to retrieve AML Purpose Codes based on provided
	 * parameters.
	 * 
	 * @param body The JSON body containing request data from client, including
	 *             channelId, transactionType, and customerType
	 * @param ex   The Exchange object for handling exceptions
	 * @return JSON response containing AML purpose codes retrieved from the
	 *         database
	 * @throws Exception If there is an error during database connection or
	 *                   procedure execution
	 */
	public ObjectNode BancsAMLPurposeCodeStoreProcedure(
			@Simple("${body[GetAMLPurposeCodesRequest][channelId]}") String channelId,
			@Simple("${body[GetAMLPurposeCodesRequest][transactionType]}") String transactionType,
			@Simple("${body[GetAMLPurposeCodesRequest][customerType]}") String customerType, Exchange ex)
			throws Exception {

		Connection conn = null;
		OracleCallableStatement ocStatement = null;
		ResultSet rs = null;

		try {

			conn = dataSource.getConnection();

			String strProcedure = "CALL selectamlpurpose(?,?,?,?)";

			ocStatement = (OracleCallableStatement) conn.prepareCall(strProcedure);

			ocStatement.setString(1, channelId);
			ocStatement.setString(2, transactionType);
			ocStatement.setString(3, customerType);
			ocStatement.registerOutParameter(4, OracleTypes.CURSOR);

			ocStatement.execute();

			rs = (ResultSet) ocStatement.getObject(4);
			ResultSetMetaData rsMetadata = null;
			rsMetadata = rs.getMetaData();

			ObjectNode oGetAMLPurposeCodesResponseNode = JsonNodeFactory.instance.objectNode();
			ObjectNode oGetAMLPurposeCodesResponse = oGetAMLPurposeCodesResponseNode
					.putObject("GetAMLPurposeCodesResponse");
			ArrayNode orecordArrayNode = oGetAMLPurposeCodesResponse.putArray("record");

			while (rs.next()) {

				ObjectNode orecordObjectNode = JsonNodeFactory.instance.objectNode();

				for (int i = 1; i <= rsMetadata.getColumnCount(); i++) {
					String columnName = rsMetadata.getColumnName(i);
					String columnValue = rs.getString(i);

					orecordObjectNode.put(columnName, columnValue);
				}
				orecordArrayNode.add(orecordObjectNode);
			}

			return oGetAMLPurposeCodesResponseNode;

		} catch (Exception e) {
			e.printStackTrace();
			ex.getIn().setBody(e.getMessage());
		} finally {
			try {

				if (rs != null) {
					rs.close();
				}
				if (ocStatement != null) {
					ocStatement.close();
				}

				if (null != conn)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
				ex.getIn().setBody(e.getMessage());
			}
		}
		return null;
	}

}
