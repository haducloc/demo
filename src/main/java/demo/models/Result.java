package demo.models;

/**
 *
 * @author <a href="mailto:haducloc13@gmail.com">Loc Ha</a>
 *
 */
public class Result {

	private int code;
	private String message;

	private Object data;

	public Result asError() {
		code = 1;
		return this;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Result code(int code) {
		setCode(code);
		return this;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Result message(String message) {
		setMessage(message);
		return this;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Result data(Object data) {
		setData(data);
		return this;
	}
}
