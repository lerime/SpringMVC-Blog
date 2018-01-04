package com.tugaydemirel.database;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.tugaydemirel.enums.CommentEnum;
import com.tugaydemirel.properties.Comment;

public class CommentCrud extends DB implements CrudProcesses {

	

	@Override
	public boolean update(Object o) {
		Comment comment = (Comment) o;
		
		PreparedStatement pStatement = null;
		
		
		try {
			pStatement = (PreparedStatement) super.preBaglan("CALL updateComment(?,?)");
			pStatement.setInt(1, comment.getId());
			pStatement.setBoolean(2, comment.isRead());
			
			int isUpdate = pStatement.executeUpdate();
			if (isUpdate > 0 ) {
				System.out.println("update basarili");
				return true;
			}
		} catch (Exception e) {
			System.err.println("CommentCrud update() : " + e);
		}finally {
			super.kapat();
		}
		
		
		return false;
	}

	@Override
	public ArrayList<Comment> read(Object o) {
		System.out.println("comment crud read");
		ArrayList<Comment> comments = new ArrayList<Comment>();
		PreparedStatement pStatement = null;
		Comment comment = (Comment) o;
		try {

			if (o == null) {
				pStatement = (PreparedStatement) super.preBaglan("CALL allComment()");
			} else if (comment.getWritingId() > 0) {
				pStatement = (PreparedStatement) super.preBaglan("CALL getCommentForWriting(?)");
				pStatement.setInt(1, comment.getWritingId());
			} else if (comment.getId() >0) {
				pStatement = (PreparedStatement) super.preBaglan("CALL getCommentForId(?)");
				pStatement.setInt(1, comment.getId());
			} else {
				pStatement = (PreparedStatement) super.preBaglan("CALL getCommentForRead(?)");
				pStatement.setBoolean(1, comment.isRead());

			}
			ResultSet rSet = pStatement.executeQuery();
			while (rSet.next()) {
				comment = new Comment();
				comment.setId(rSet.getInt("" + CommentEnum.comment_id));
				comment.setName(rSet.getString("" + CommentEnum.comment_name));
				comment.setEmail(rSet.getString("" + CommentEnum.comment_email));
				comment.setContent(rSet.getString("" + CommentEnum.comment_content));
				comment.setWebsite(rSet.getString("" + CommentEnum.comment_website));
				comment.setWritingId(rSet.getInt("" + CommentEnum.comment_writingId));
				comment.setDate(rSet.getDate("" + CommentEnum.comment_date));
				comments.add(comment);
			}
		} catch (Exception e) {
			System.err.println("Commentcrud read():" + e);
		} finally {
			super.kapat();
		}
		return comments;
	}

	@Override
	public boolean create(Object o) {
		Comment comment = (Comment) o;
		java.sql.Date sqlDate = new java.sql.Date(comment.getDate().getTime());

		try {
			PreparedStatement pStatement = (PreparedStatement) super.preBaglan("CALL insertComment(?,?,?,?,?,?,?)");
			pStatement.setString(1, comment.getName());
			pStatement.setString(2, comment.getEmail());
			pStatement.setString(3, comment.getWebsite());
			pStatement.setString(4, comment.getContent());
			pStatement.setInt(5, comment.getWritingId());
			pStatement.setDate(6, sqlDate);
			pStatement.setBoolean(7, comment.isRead());

			int isCreate = pStatement.executeUpdate();

			if (isCreate > 0) {
				System.out.println("comment create basarili");
				return true;
			}

		} catch (Exception e) {
			System.err.println("CommentCrud.create() : " + e);
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
