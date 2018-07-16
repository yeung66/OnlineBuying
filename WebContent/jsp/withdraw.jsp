<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>提现</title>
<link href="../css/alipay.css" rel="stylesheet" type="text/css" media="all">
</head>
<body text=#000000 bgColor="#ffffff" leftMargin=0 topMargin=4>
	<!--插入head-->
    <jsp:include page="head.jsp"></jsp:include>
    <!--提现-->
	<div id="main">
	<div class="center">
			<h2 id="tab1" class="selected">提  现</h2>
		<form name=alipayment action=../WithDraw method=post
			target="_blank">
			<div id="body1" class="show" name="divcontent">
				<dl class="content">
					<dt>商户订单号 ：</dt>
					<dd>
						<input id="WIDout_trade_no" name="WIDout_trade_no" readonly="readonly"/>
					</dd>
					<hr class="one_line">
					<dt>订单名称 ：</dt>
					<dd>
						<input id="WIDsubject" name="WIDsubject" readonly="readonly"/>
					</dd>
					<hr class="one_line">
					<dt>支付宝账号 ：</dt>
					<dd>
						<input id="WIDaccount" name="WIDaccount" />
					</dd>
					<hr class="one_line">
					<dt>真实姓名 ：</dt>
					<dd>
						<input id="WIDreal_name" name="WIDreal_name" />
					</dd>
					<hr class="one_line">
					<dt>提现金额 ：</dt>
					<dd>
						<input id="WIDtotal_amount" name="WIDtotal_amount" />
					</dd>
					<hr class="one_line">
					<dt></dt>
					<dd id="btn-dd">
						<span class="new-btn-login-sp">
							<button class="new-btn-login" type="submit"
								style="text-align: center;">提 现</button>
						</span>
						<span class="note-help">如果您点击“提现”按钮，即表示您同意该次的执行操作。</span>
					</dd>
				</dl>
			</div>
		</form>
		</div>
		<div id="foot">
			<ul class="foot-ul">
				<li>珞珈商城版权所有 2018</li>
			</ul>
		</div>
	</div>
</body>
<script language="javascript">
	var contents = document.getElementsByName('divcontent');

	function GetDateNow() {
		var vNow = new Date();
		var sNow = "";
		sNow += String(vNow.getFullYear());
		sNow += String(vNow.getMonth() + 1);
		sNow += String(vNow.getDate());
		sNow += String(vNow.getHours());
		sNow += String(vNow.getMinutes());
		sNow += String(vNow.getSeconds());
		sNow += String(vNow.getMilliseconds());
		document.getElementById("WIDout_trade_no").value =  sNow;
		document.getElementById("WIDsubject").value = "提现";
		document.getElementById("WIDtotal_amount").value = "0.01";
	}
	GetDateNow();
</script>
</html>