package com.tugaydemirel.database;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.tugaydemirel.enums.PictureEnum;
import com.tugaydemirel.properties.Picture;

public class PictureCrud extends DB implements CrudProcesses {

	@Override
	public boolean update(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Picture> read(Object o) {
		Picture picture = (Picture) o;
		ArrayList<Picture> pictures = new ArrayList<Picture>();
		
		try {
			PreparedStatement pStatement = (PreparedStatement) super.preBaglan("CALL getPictureForId(?)");
			pStatement.setInt(1, picture.getId());
			
			ResultSet rSet = pStatement.executeQuery();
			while (rSet.next()) {
				picture = new Picture();
				picture.setId(rSet.getInt(""+PictureEnum.urun_id));
				picture.setName(rSet.getString(""+PictureEnum.adi));
				picture.setDirectory(rSet.getString(""+PictureEnum.klasor));
				pictures.add(picture);
			}
		} catch (Exception e) {
			System.err.println("Picturecrud, read:"+e);
		} finally {
            super.kapat();
        }
		return pictures;
	}

	@Override
	public boolean create(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

}
