package kr.co.address.common.log;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;

/**
 * <pre>
 * request 값에서 body 값 추출.
 * </pre>
 *
 * Created on 2019. 01. 20.
 * 
 */
public class RequestBodyWrapper extends HttpServletRequestWrapper  {
	
	/**
	 * Tab키
	 */
	private final static String TAB_STR = "\t";

	/**
	 * Enter키
	 */
	private final static String LINE_STR = "\n";

	/**
	 * Space bar키
	 */
	private final static String SPACE_STR = "\n";
	
	
	private byte[] bytes;
	private String requestBody;

	public RequestBodyWrapper(HttpServletRequest request) throws IOException{
		super(request);

		InputStream in = super.getInputStream();
		
		requestBody = IOUtils.toString(in, "UTF-8");
		requestBody = requestBody.replace(TAB_STR, "");
		requestBody = requestBody.replace(LINE_STR, "");
		requestBody = requestBody.replace(SPACE_STR, "");
		
		
		bytes =  requestBody.getBytes();
		requestBody = new String(bytes);
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		/*final ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		return  new ServletImpl(bis);*/
		
		final ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		return new ServletImpl(new ByteArrayInputStream(bytes));
	}

	public String getRequestBody() {
		return this.requestBody;
	}

	class ServletImpl extends ServletInputStream{
		private InputStream is;
		public ServletImpl(InputStream bis){
			is = bis;
		}

		@Override
		public int read() throws IOException {
			return is.read();
		}

		@Override
		public int read(byte[] b) throws IOException {
			return is.read(b);
		}

		@Override
		public boolean isFinished() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isReady() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setReadListener(ReadListener listener) {
			// TODO Auto-generated method stub
			
		}
	}
	
}