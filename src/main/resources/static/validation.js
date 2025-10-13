
document.querySelector("form").addEventListener("submit", function(e) {
	let msgs = [];
	//社員ID：数値のみ
	let id = document.querySelector("input[name='id']").value.trim();
	if (id !== "" && !/^\d+$/.test(id)) {
		msgs.push("社員IDは数値のみ入力可能です。");
	}


	//年齢：数値のみ＆相関チェック

	let ageFrom = document.querySelector("input[name='ageFrom']").value.trim();
	let ageTo = document.querySelector("input[name='ageTo']").value.trim();

	if (ageFrom !== "" && isNaN(ageFrom)) {
		msgs.push("年齢（FROM）は数値で入力してください。");
	}
	if (ageTo !== "" && isNaN(ageTo)) {
		msgs.push("年齢（To）は数値で入力してください。");
	}
	if (ageFrom !== "" && ageTo !== "" && parseInt(ageFrom) > parseInt(ageTo)) {
		msgs.push("年齢の範囲指定が正しくありません（From≦Toにしてください）。");
	}

	//日付チェック
	function checkDate(field, label) {
		if (field && isNaN(Date.parse(field))) {
			return `${label}は日付形式（yyyy-MM-dd）で入力してください。`;

		}
		return null;
	}
	//開始日・終了日：日付型＆相関チェック				
	let startFrom = document.querySelector("input[name='startDateFrom']").value;
	let startTo = document.querySelector("input[name='startDateTo']").value;
	let endFrom = document.querySelector("input[name='endDateFrom']").value;
	let endTo = document.querySelector("input[name='endDateTo']").value;

	[checkDate(startFrom, "開始日（From）"),
	checkDate(startTo, "開始日（To）"),
	checkDate(endFrom, "終了日（From）"),
	checkDate(endTo, "終了日（To）")
	].filter(Boolean).forEach(msg => msgs.push(msg));

	if (startFrom && startTo && new Date(startFrom) > new Date(startTo)) {
		msgs.push("開始日の範囲指定が正しくありません（From ≦ To にしてください）。");
	}
	if (endFrom && endTo && new Date(endFrom) > new Date(endTo)) {
		msgs.push("終了日の範囲指定が正しくありません（From ≦ To にしてください）。");
	}

	//エラーがあれば送信キャンセル
	if (msgs.length > 0) {
		e.preventDefault();
		alert(msgs.join("\n"));
	}
});