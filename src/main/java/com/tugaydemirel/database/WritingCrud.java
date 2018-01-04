package com.tugaydemirel.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.tugaydemirel.enums.WritingEnum;
import com.tugaydemirel.properties.Writing;

public class WritingCrud extends DB implements CrudProcesses {

	@Override
	public boolean update(Object o) {
		System.out.println("update writing");
		Writing writing = (Writing) o;
		java.sql.Date sqlDate = new java.sql.Date(writing.getDate().getTime());
		
		System.out.println(writing.getTitle());
		System.out.println(writing.getId());
		System.out.println(writing.getWriter());

		System.out.println("update writing trydan once");
		try {
			PreparedStatement pStatement = (PreparedStatement) super.preBaglan("CALL updateWriting(?,?,?,?,?,?,?)");
			System.out.println("icerde");
			pStatement.setInt(1, writing.getId());
			pStatement.setString(2, writing.getWriter());
			pStatement.setString(3, writing.getTitle());
			pStatement.setString(4, writing.getContent());
			pStatement.setString(5, writing.getCategory());
			pStatement.setDate(6, sqlDate);
			pStatement.setInt(7, writing.getWriterId());

			System.out.println("executedan once");
			int isUpdate = pStatement.executeUpdate();
			if (isUpdate > 0) {
				System.out.println("Yazi update basarili");
				return true;
			}
			System.out.println("her sey bitti");
		} catch (Exception e) {
			System.err.println("WritingCrud sinifi, update():" + e);
		} finally {
            super.kapat();
        }
		return false;
	}

	@Override
	public ArrayList<Writing> read(Object o) {
		ArrayList<Writing> writingList = new ArrayList<Writing>();
		Writing writing = null;
		PreparedStatement pStatement = null;
		try {
			if (o != null) {
				writing = (Writing) o;
				if (writing.getId() == -1) {
					System.out.println("Title durumu:" + writing.getTitle());
					System.out.println("getWritingForCategory(?)");
					pStatement = (PreparedStatement) super.preBaglan("CALL getWritingForCategory(?)");
					pStatement.setString(1, writing.getCategory());
				} else if (!(writing.getTitle() == null)) {
					
					System.out.println("getWritingForTitle(?)");
					writing = (Writing) o;
					pStatement = (PreparedStatement) super.preBaglan("CALL getWritingForTitle(?)");
					pStatement.setString(1, writing.getTitle());
				}else {
					System.out.println("getWritingForId(?)");
					writing = (Writing) o;
					pStatement = (PreparedStatement) super.preBaglan("CALL getWritingForId(?)");
					pStatement.setInt(1, writing.getId());
				}

			} else {
				System.out.println("AllWriting()");
				pStatement = (PreparedStatement) super.preBaglan("CALL allWriting()");
			}

			
			ResultSet rSet = pStatement.executeQuery();

			while (rSet.next()) {
				writing = new Writing();
				writing.setId(rSet.getInt("" + WritingEnum.writing_id));
				writing.setTitle(rSet.getString("" + WritingEnum.writing_title));
				writing.setContent(rSet.getString("" + WritingEnum.writing_content));
				writing.setWriter(rSet.getString("" + WritingEnum.writing_writer));
				writing.setDate(rSet.getDate("" + WritingEnum.writing_date));
				writing.setCategory(rSet.getString("" + WritingEnum.writing_category));
				writing.setWriterId(rSet.getInt(""+ WritingEnum.writing_writerId));
				System.out.println("writingcrud rs while: id:"+writing.getId());
				writingList.add(writing);

			}
		} catch (SQLException e) {
			System.err.println("WritingCrud , read()" + e);
		} finally {
            super.kapat();
        }
		return writingList;
	}

	@Override
	public boolean create(Object o) {
		Writing writing = (Writing) o;
		java.sql.Date sqlDate = new java.sql.Date(writing.getDate().getTime());

		try {
			PreparedStatement pStatement = (PreparedStatement) super.preBaglan("CALL insertWriting(?,?,?,?,?,?)");

			pStatement.setString(1, writing.getWriter());
			pStatement.setString(2, writing.getTitle());
			pStatement.setString(3, writing.getContent());
			pStatement.setString(4, writing.getCategory());
			pStatement.setDate(5, sqlDate);
			pStatement.setInt(6, writing.getWriterId());

			int isUpdate = pStatement.executeUpdate();
			if (isUpdate > 0) {
				System.out.println("Yazi olusturma basarili");
				return true;
			}
		} catch (Exception e) {
			System.err.println("WritingCrud sinifi, create():" + e);
		} finally {
            super.kapat();
        }
		return false;
	}

	@Override
	public boolean delete(Object o) {
		Writing writing = new Writing();
		if (o != null) {
			writing = (Writing) o;
		}
		try {
			PreparedStatement pStatement  = (PreparedStatement) super.preBaglan("CALL delWriting(?)");
			pStatement.setInt(1, writing.getId());
			
			int isUpdate = pStatement.executeUpdate();
			if (isUpdate > 0) {
				System.out.println("Yazi silme basarili");
				return true;
			}
		} catch (Exception e) {
			System.err.println("WritingCrud , delete():"+e);
		} finally {
            super.kapat();
        }
		return false;
	}

}
