package com.example.camelSnsRoute;

public class SNSResponseConverted{
	private String signatureVersion;
	private String type;
	private String topicArn;
	private String message;
	private String unsubscribeURL;
	private String signature;
	private String timestamp;
	private String signingCertURL;
	private String subject;
	private String messageId;

	public String getSignatureVersion(){
		return signatureVersion;
	}

	public String getType(){
		return type;
	}

	public String getTopicArn(){
		return topicArn;
	}

	public String getMessage(){
		return message;
	}

	public String getUnsubscribeURL(){
		return unsubscribeURL;
	}

	public String getSignature(){
		return signature;
	}

	public String getTimestamp(){
		return timestamp;
	}

	public String getSigningCertURL(){
		return signingCertURL;
	}

	public String getSubject(){
		return subject;
	}

	public String getMessageId(){
		return messageId;
	}
}
