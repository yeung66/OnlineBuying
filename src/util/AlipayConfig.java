package util;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016091800539417";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCkRb8SBZpcZskUeO2QCsBm0Se5xSNxZxiV9d9x2N05VaRlFrbQ+5w5mL7GriG12h0yQ8BOzPO6YZFO5frSoIimuGezUo3PwmWfCPdtDLX1mnHDjRZNROQohOW4b/F16cJBEoUKKKhW2rdZpqWwIUPATr9cMh2xEuxKDutVfq+qTn2bypiNVlQnnaZOPsLzisMcWbCSdLxpCM2+KgfS3nXnz+3RGJF5/aIYGYBRGSE+clxtdve5cF3MbCh7V0eOqX/4a2WOe+y2sTBeYvVNU3/LW2LlOGCpPY3dcmL4jJUTFiNpoBjngqV+i/ItIRUOJe231MtOtc6ouwbHV/gwE+W1AgMBAAECggEAIFCIEaeAggTGMXzIXrl+t7sVMXoSStCZCYbgeajLe+fY+vnI5LtaNApUHQlMIFXTwHuLbPTsTRG8X+TlvmFOAF3uKFxpASGgk/RTl+QUN0g2cN1kMbcHmuDpv6pluWhOijZ2bVUqKEvGHaCmn0sr5LyY8C37oUWzra3ZpWvSapHMIo6XMK6r3tyEzRIszGQbbUG/ZxO3XmFDINLDy1vOo+Fm1v7rf7IH926BL9bq9VUWDp4VO8njnUYQNd1O6eRRZNxUlVTyuSqcm6Sn+899aC/+hN0EqDYLW1D46br/LREaQxZp6icHGayeyX71cjXBxMsAZ+GxB9zNI1cjdWWnUQKBgQD2QDlFmjJ8aNMaf49xR7i6I9BvAlE9c+aK8HjnCepxb1wQ1DKK+r5kIK0t9sZwJN3sVQcIJRs+b2ajme3l7Rd5u0SyW9IgfzjpkaAQcdTcRSnNrBD+iuzE53fhjoJBwtG3aBpCp/E59NntJ+WdMUxWk71J19/D34+HjMjdz6GDowKBgQCqxqnVOlnX9AA/NMWFpXK5RsAg5rvfLxrDwUNsmPxlsqxc3GCI8nfhuGMEJeXRgIThw68Bae9OZuq41B3ohIO9xrApwKbv6vP96yhVFNpa7rHOoisWFJJSlZ/fUz74DFmQOMrC75BxKdR7kOnWCtrw7LblaH9JlViDb4hgGthGxwKBgQCDJFTb4nC8nL7AP+rOc1nHl8crN1q4lX7Tw5k/waIfYyTuuXNpXHwEqvpPnjq0Ao5FjErAZbKKn51Tx2hQqOF1rOu66PeQ4vAWQ72wxE6OOC85H0e1p5rMilDnyJNKHky9li1WDkaDjQh6KmM4Qcbb3LZUGylKEUdI8wsORcNIbQKBgQCC25vDgeTaL2QJCrzX/cD865PJIkSPbh7KJUsu6cNtgg3FUjC+f10RHyLwSMHjWOF5xWJSaQQwRXhSjGcmhnQUdS0DsVW5vYWc6haLq2DuEL06otT1JGpC0dghXj5//6aZdosX+E3sCKuCy7YCh2rg9eHnw7zVe5TJkisjyS40fQKBgGo7X3riunCJHr7f+sFPkLCHxJd0kdG7Nln7eDTJd3ljfUtr/ukJga/+uJRWmztTxexpxPtRiJKGHV+QUUeO2EGOva3qVVpdKdXXYYjXokNfXyji9+JMo5dyBkg5nOe2HKVVGztChB5ltGfczwNthWcJexs9uqj2VR+VO927u5sr";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlOeT5KUCvGz9MutNqCHb7h3g2O1axjI/SN/oLJ8iW8lczjSEEwNsrZ07FOJh+983oDL5p5ZLPM4AGj7pj9H79GcrDRMJ+qOUTr1aa0fnkp2WglH3wL1rzFtGvGdpgtbL3nentkqmTUkKjHcrWQRn7on7eGuRvSjiUemowuQcTwpbcY91d0eTPueUfSrCKTrV/lYx8mWVn5OkOdwxzEN1j3XNkpHvbsrAcQKTJPaVefaPC99YdJqwd2o3GMXXPr2tNvkXFYS6E5vP+pkI5wPWRQgoyAHXKmikY6/+ioXE3AWtGWN48JS8AeqMWyKNCiS6qbLh+7EPoRfdOAgP7FKIuQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://120.78.150.152:8080/shixun/jsp/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://120.78.150.152:8080/shixun/jsp/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

