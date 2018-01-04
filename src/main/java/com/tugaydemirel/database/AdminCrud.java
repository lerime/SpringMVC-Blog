package com.tugaydemirel.database;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sound.midi.Soundbank;

import com.mysql.jdbc.PreparedStatement;
import com.tugaydemirel.enums.AdminEnum;
import com.tugaydemirel.properties.Admin;

public class AdminCrud extends DB implements CrudProcesses {

	@Override
	public boolean update(Object o) {
		Admin admin = (Admin) o;
		 
		try {
			PreparedStatement pStatement = (PreparedStatement) super.preBaglan("CALL updateAdmin(?,?,?,?,?,?,?)");
			
			pStatement.setInt(1, admin.getId());
			pStatement.setString(2, admin.getName());
			pStatement.setString(3, admin.getSurname());
			pStatement.setString(4, admin.getEmail());
			pStatement.setString(5, admin.getPassword());
			pStatement.setString(6, admin.getJob());
			pStatement.setString(7, admin.getDescription());

			int isCreate = pStatement.executeUpdate();

			if (isCreate > 0) {
				System.out.println("update admin successfully");
				return true;
			}
		} catch (Exception e) {
			System.err.println("update admin error:"+e);
		}finally {
            super.kapat();
        }
		return false;
	}

	@Override
	public ArrayList<Admin> read(Object o) {
		ArrayList<Admin> adminList = new ArrayList();
		Admin admin = null;

		try {
			PreparedStatement pStatement = null;
			if (o == null) {
				pStatement = (PreparedStatement) super.preBaglan("CALL allAdmins()");
			}else {
				admin = (Admin) o;
				pStatement = (PreparedStatement) super.preBaglan("CALL getAdminForId(?)");
				pStatement.setInt(1, admin.getId());
			}


			 ResultSet rs = pStatement.executeQuery();
			while (rs.next()) {
				admin = new Admin();
				admin.setId(rs.getInt("" + AdminEnum.admin_id));
				admin.setName(rs.getString("" + AdminEnum.admin_name));
				admin.setSurname(rs.getString("" + AdminEnum.admin_surname));
				admin.setEmail(rs.getString("" + AdminEnum.admin_email));
				admin.setPassword(rs.getString("" + AdminEnum.admin_password));
				admin.setJob(rs.getString(""+ AdminEnum.admin_job));
				admin.setDescription(rs.getString(""+ AdminEnum.admin_description));
				adminList.add(admin);
			}
		} catch (Exception e) {
			System.err.println("allAdmins():" + e);
		} finally {
			super.kapat();
		}
		return adminList;
	}
	
	public Admin read(String mail) {
		if (!mail.equals("")) {
			Admin admin = null;
			try {
				PreparedStatement pStatement = (PreparedStatement) super.preBaglan("CALL getAdminForMail(?)");
				pStatement.setString(1, mail);
				
				ResultSet rSet = pStatement.executeQuery();
				
				while (rSet.next()) {
					admin = new Admin();
					admin.setEmail(rSet.getString(""+AdminEnum.admin_email));
					return admin;
				}
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
	            super.kapat();
	        }
		}
		return null;
	}

	@Override
	public boolean create(Object o) {
		Admin admin = (Admin) o;
		
		try {
			PreparedStatement pStatement = (PreparedStatement) super.preBaglan("CALL insertAdmin(?,?,?,?,?,?)");
			pStatement.setString(1, admin.getName());
			pStatement.setString(2, admin.getSurname());
			pStatement.setString(3, admin.getEmail());
			pStatement.setString(4, admin.getPassword());
			pStatement.setString(5, admin.getJob());
			pStatement.setString(6, admin.getDescription());

			int isCreate = pStatement.executeUpdate();

			if (isCreate > 0) {
				System.out.println("create admin successfully");
				return true;
			}
			
		} catch (Exception e) {
			System.err.println("create admin error:"+e);
		} finally {
            super.kapat();
        }
		return false;
	}

	@Override
	public boolean delete(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

}
