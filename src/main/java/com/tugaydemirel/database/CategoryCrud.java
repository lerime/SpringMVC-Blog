package com.tugaydemirel.database;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.tugaydemirel.enums.CategoryEnum;
import com.tugaydemirel.properties.Category;

public class CategoryCrud extends DB implements CrudProcesses {

	@Override
	public boolean update(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Category> read(Object o) {

		ArrayList<Category> categoryList = new ArrayList<Category>();
		Category category = null;
		PreparedStatement pStatement = null;
		try {
			if (o != null) {
				category = (Category) o;
				System.out.println("obje null degil");
				System.out.println(category.getId());
				System.out.println(category.getName());
				
				if (category.getName() != null) {
					System.out.println("category crud if");
					pStatement = (PreparedStatement) super.preBaglan("CALL getCategoryForName(?)");
					pStatement.setString(1, category.getName());
				} else {
					System.out.println("categorycrud else");
					pStatement = (PreparedStatement) super.preBaglan("CALL getCategoryForId(?)");
					pStatement.setInt(1, category.getId());
				}

			} else {
				System.out.println( "en else");
				pStatement = (PreparedStatement) super.preBaglan("CALL allCategory()");
			}

			ResultSet rSet = pStatement.executeQuery();

			while (rSet.next()) {
				category = new Category();
				category.setId(rSet.getInt("" + CategoryEnum.category_id));
				category.setName(rSet.getString("" + CategoryEnum.category_name));
				categoryList.add(category);
			}

		} catch (Exception e) {
			System.err.println("CategoryCrud, read():" + e);
		} finally {
            super.kapat();
        }

		return categoryList;
	}

	@Override
	public boolean create(Object o) {
		Category category = (Category) o;

		try {
			PreparedStatement pStatement = (PreparedStatement) super.preBaglan("CALL insertCategory(?)");

			pStatement.setString(1, category.getName());

			int isUpdate = pStatement.executeUpdate();
			if (isUpdate > 0) {
				System.out.println("category create başarılı");
				return true;
			}
		} catch (Exception e) {
			System.err.println("CategoryCrud sinifi , create()" + e);
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
