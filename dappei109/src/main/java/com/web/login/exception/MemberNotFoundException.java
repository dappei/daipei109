package com.web.login.exception;

public class MemberNotFoundException extends RuntimeException {
	Integer bookId;

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public MemberNotFoundException(Integer bookId) {
		this.bookId = bookId;
	}

	public MemberNotFoundException() {
	}

	public MemberNotFoundException(String message) {
		super(message);
	}

	public MemberNotFoundException(Throwable cause) {
		super(cause);
	}
	
	public MemberNotFoundException(String message, Integer bookId) {
		super(message);
		this.bookId = bookId;
	}
	public MemberNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public MemberNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
