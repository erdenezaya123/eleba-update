window.onload = function() {
	//获取显示隐藏按钮DOM对象
	let showBtn = document.getElementById('showBtn');
	// 支付方式选择
	let showZFB = document.getElementById('showZFB');
	let showWX = document.getElementById('showWX');
	let showSZRMB = document.getElementById('showSZRMB');
	let showJTYH = document.getElementById('showJTYH');
	let showYSF = document.getElementById('showYSF');
	// 图片信息→点击图片选择（新加）
	let selZFB = document.getElementById('selZFB');
	let selWX = document.getElementById('selWX');
	let selSZRMB = document.getElementById('selSZRMB');
	let selJTYH = document.getElementById('selJTYH');
	let selYSF = document.getElementById('selYSF');
	//获取订单明细DOM对象
	let detailetBox = document.getElementById('detailetBox');
	//设置默认订单明细隐藏
	detailetBox.style.display = 'none';
	// 设置默认支付方式
	showZFB.style.display = 'block';
	showWX.style.display = 'none';
	showSZRMB.style.display = 'none';
	showJTYH.style.display = 'none';
	showYSF.style.display = 'none';

	showBtn.onclick = function() {

		//判断订单明细DOM对象是否隐藏，如果是就显示，否则就隐藏
		if (detailetBox.style.display == 'none') {
			detailetBox.style.display = 'block';
		} else {
			detailetBox.style.display = 'none';
		}
	}

	selZFB.onclick = function() {

		//判断支付宝DOM对象是否隐藏，如果是就显示，否则就隐藏
		if (showZFB.style.display == 'none') {
			showZFB.style.display = 'block';
			showWX.style.display = 'none';
			showSZRMB.style.display = 'none';
			showJTYH.style.display = 'none';
			showYSF.style.display = 'none';
		}
	}

	selWX.onclick = function() {

		//判断微信DOM对象是否隐藏，如果是就显示，否则就隐藏
		if (showWX.style.display == 'none') {
			showZFB.style.display = 'none';
			showWX.style.display = 'block';
			showSZRMB.style.display = 'none';
			showJTYH.style.display = 'none';
			showYSF.style.display = 'none';
		}
	}

	selSZRMB.onclick = function() {

		//判断数字人民币DOM对象是否隐藏，如果是就显示，否则就隐藏
		if (showSZRMB.style.display == 'none') {
			showZFB.style.display = 'none';
			showWX.style.display = 'none';
			showSZRMB.style.display = 'block';
			showJTYH.style.display = 'none';
			showYSF.style.display = 'none';
		}
	}

	selJTYH.onclick = function() {

		//判断交通银行DOM对象是否隐藏，如果是就显示，否则就隐藏
		if (showJTYH.style.display == 'none') {
			showZFB.style.display = 'none';
			showWX.style.display = 'none';
			showSZRMB.style.display = 'none';
			showJTYH.style.display = 'block';
			showYSF.style.display = 'none';
		}
	}

	selYSF.onclick = function() {

		//判断云闪付DOM对象是否隐藏，如果是就显示，否则就隐藏
		if (showYSF.style.display == 'none') {
			showZFB.style.display = 'none';
			showWX.style.display = 'none';
			showSZRMB.style.display = 'none';
			showJTYH.style.display = 'none';
			showYSF.style.display = 'block';
		}
	}
}