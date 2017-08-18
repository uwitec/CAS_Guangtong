function isTel_xk(data){
	return /^((\d{3}-\d{8}|\d{4}-\d{7,8})|(1[3|5|7|8][0-9]{9}))$/.test(data);
};
function isId_xk(data){
	return /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/.test(data);
};
function isEmail_xk(data){
	return /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/.test(data);
};
function isUsername_xk(data){
	return /^[a-zA-Z][a-zA-Z0-9_]{4,15}$/.test(data);
};
function isPassword_xk(data){
	return /^[a-zA-Z0-9]{5,16}$/.test(data);
};
function isBlank_xk(data){
	return data==""||data==null||data==undefined;
}