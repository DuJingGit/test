package com.dj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Main {

	public static void main(String[] args) {
		try {

			// weblogic ������ʹ��
			Hashtable ht = new Hashtable();
			ht.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			ht.put(Context.PROVIDER_URL, "t3://192.168.1.66:7001");
			ht.put(Context.SECURITY_PRINCIPAL, "weblogic");// �û���
			ht.put(Context.SECURITY_CREDENTIALS, "p@ssw0rd");// ����

			Context ctx;
			ctx = new InitialContext(ht);

			DataSource source = (DataSource) ctx.lookup("test");
			Connection connection = source.getConnection();
			String sql = "select name from user where id = 1";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
