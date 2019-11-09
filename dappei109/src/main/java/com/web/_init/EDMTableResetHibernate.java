package com.web._init;

/*  
    程式說明：建立表格與設定初始測試資料。
    表格包括：Book, BookCompany, Member, Orders, OrderItems
 
*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Clob;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.web._init.util.HibernateUtils;
import com.web._init.util.SystemUtils2018;
import com.web.event.model.EventBean;
import com.web.login.model.MemberBean;

public class EDMTableResetHibernate {
	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8的BOM字元

	public static void main(String args[]) {

		String line = "";

		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
//1. event資料表
			File file = new File("data/event.dat");
			// 由"data/event.dat"逐筆讀入Event表格內的初始資料，然後依序新增到Event表格中
			try (FileInputStream fis = new FileInputStream(file);
					InputStreamReader isr = new InputStreamReader(fis, "UTF8");
					BufferedReader br = new BufferedReader(isr);) {
				while ((line = br.readLine()) != null) {
					System.out.println("line=" + line);
					// 去除 UTF8_BOM: \uFEFF
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] token = line.split("\\|");
					EventBean event = new EventBean();
					event.setEventName(token[0]);
					event.setEventStartTime(token[1]);
					event.setEventEndTime(token[2]);
					event.setLocation(token[3]);
					event.setMaxPeople(Integer.parseInt(token[4].trim()));
					event.setPrice(Integer.parseInt(token[5].trim()));
					// 讀取圖片檔
					Blob blob = SystemUtils2018.fileToBlob(token[6].trim());
					event.setEventPic(blob);
					event.setFileName(SystemUtils2018.extractFileName(token[6].trim()));
					event.setExist(Integer.parseInt(token[7].trim()));
					session.save(event);
					System.out.println("新增一筆Event紀錄成功");
				}
				// 印出資料新增成功的訊息
				session.flush();
				System.out.println("Event資料新增成功");
			}
			tx.commit();
		} catch (Exception e) {
			System.err.println("新建表格時發生例外: " + e.getMessage());
			e.printStackTrace();
			tx.rollback();
		}
		
//			File file = new File("data/member.dat");
////2.Member資料表, 由"data/Input.txt"逐筆讀入Member表格內的初始資料，
//			// 然後依序新增到Member表格中
//			try (FileInputStream fis = new FileInputStream("data/Input.txt");
//					InputStreamReader isr0 = new InputStreamReader(fis, "UTF-8");
//					BufferedReader br = new BufferedReader(isr0);) {
//				while ((line = br.readLine()) != null) {
//					System.out.println("line=" + line);
//					// 去除 UTF8_BOM: \uFEFF
//					if (line.startsWith(UTF8_BOM)) {
//						line = line.substring(1);
//					}
//					String[] sa = line.split("\\|");
//					MemberBean bean = new MemberBean();
//					bean.setMemberId(Integer.parseInt(sa[0].trim()));
//					bean.setAccount(sa[1]);
//					bean.setPassword(sa[2]);
//					bean.setUsername(sa[3]);
//					bean.setGender(sa[4]);
//					bean.setEmail(sa[5]);
//					bean.setPhone(sa[6]);
//					bean.setBirthday(sa[7]);
//					bean.setStatus(Integer.parseInt(sa[8].trim()));
//					Blob bb = SystemUtils2018.fileToBlob(sa[98].trim());
//					bean.setFacepic(bb);
//					bean.setFilename(SystemUtils2018.extractFileName(sa[9].trim()));
//					bean.setTs(new java.sql.Timestamp(System.currentTimeMillis()));
//					session.save(bean);
//					System.out.println("新增會員紀錄記錄");
//				}
//				tx.commit();
//			}catch (Exception ex) {
//				tx.rollback();
//				ex.printStackTrace();
//			}
		session.close();
		factory.close();
	}
}