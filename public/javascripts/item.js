var ItemLister = ItemLister || {}; 

ItemLister.item = {
		initialize : function(){
			var instance = this;
			
			this.toggleFormIcon = $("#add-item-container i:first"); 
			this.addItemFields = $("#add-item-fields");
			
			$("#add-item-container a:first").click(instance.toggleForm);
			this.addItemFields.hide();
		},
		
		isFormVisible : function(){
			return this.toggleFormIcon.attr('class') == 'icon-minus';
		},
		
		hideForm : function(){
			this.toggleFormIcon.attr('class', 'icon-plus');
			this.addItemFields.slideUp('fast');
		},
		
		showForm : function(){
			this.toggleFormIcon.attr('class', 'icon-minus');
			this.addItemFields.slideDown('fast');
		},
		
		toggleForm : function(){
			if(ItemLister.item.isFormVisible()){
				ItemLister.item.hideForm();
			}else{
				ItemLister.item.showForm();
			}
		}
}
