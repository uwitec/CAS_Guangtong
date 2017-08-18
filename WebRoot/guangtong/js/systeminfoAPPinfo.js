var thispopup = {
				"编辑": "popupEditAPPinfo.html",
				"删除": "deleteconfirm.html",
				"+增加": "popupAddAPPinfo.html",
				"查询": "",
				"编辑权限": ""
			};
			$(".gt_wl_right a.button").unbind('click').bind('click',function(){
				var this_id = $(this).attr("zyl_id");
				$(".popwindow_xk .popup_xk").attr("this_id", this_id);
				$(".popwindow_xk .popup_xk").html(popup(thispopup[$(this).text()]));
				/*if($(this).text() == "编辑"){
					
				}*/
			});
			$(".gt_wl_right .list_xk tbody tr").unbind('dblclick').bind('dblclick',function(){
				$(".popwindow_xk .popup_xk").html(popup(thispopup["sider"]));
			});