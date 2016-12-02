package com.iscas.dsgroup.securitysearch.util;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.wltea.analyzer.IKSegmentation;
import org.wltea.analyzer.Lexeme;
import org.xml.sax.SAXException;

/**
 * 
 * @author lennyBai
 *
 */

public class CommonUtil {

	/**
	 * 更新文件内容子方法 tika抽取文件内容，分词后返回内容字符串
	 */
	public static String fileExtraction(String absFile) throws IOException, SAXException, TikaException {
		String content = null;
		File file = new File(absFile);
		String splitContent = "";

		// 使用tika抽取文件内容
		Tika tika = new Tika();
		content = tika.parseToString(file);

		// 创建分词对象
		StringReader reader = new StringReader(content);

		IKSegmentation ik = new IKSegmentation(reader, true);// 当为true时，分词器进行最大词长切分
		Lexeme lexeme = null;
		try {
			while ((lexeme = ik.next()) != null)
				// System.out.println(lexeme.getLexemeText());
				splitContent = splitContent + lexeme.getLexemeText() + " ";
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			reader.close();
		}

		return splitContent;
	}

	// 字符串拆分
	public static String ngram(String content, int max) {
		int length = content.length();
		int n = max >= length ? length : max;

		String res = "";
		for (int j = 1; j <= max; j++) {
			for (int i = 0; i < length; i++) {
				String str = "";
				if ((i + j) <= length) {
					str = content.substring(i, i + j);
				}
				res += str;
				res += " ";
			}

		}
		return res;
	}
	
}
