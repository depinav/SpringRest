Ext.onReady(function() {
	
	Ext.create('Ext.form.Panel', {
		title: 'My Form',
		bodyPadding: 5,
		width: 350,
		
		url: 'api/person/save',
		
		layout: 'anchor',
		defaults: {
			anchor: '100%'
		},
		
		defaultType: 'textfield',
		items: [{
			fieldLabel: 'Name',
			name: 'name',
			allowBlanks: false
		}],
		
		buttons: [{
			text: 'Submit',
			formBind: true,
			disabled: true,
			handler: function() {
				var form = this.up('form').getForm();
				if(form.isValid()) {
					form.submit({
						success: function(form, action) {
							Ext.Msg.alert('Success', action.result.msg);
							store.refresh();
							panel.render();
						},
						failue: function(form, action) {
							Ext.Msg.alert('Failure', action.result.msg);
						}
					});
				}
			}
		}],
		
		renderTo: Ext.getBody()
	});
	
	var model = Ext.define('Name', {
		extend: 'Ext.data.Model',
		fields: ['id', 'name'],
		proxy: {
			type: 'rest',
			url: 'api/personlist/'
		}
	});
	
	var store = Ext.create('Ext.data.Store', {
		storeId: 'nameStore',
		model: 'Name',
		autoLoad: true
	});
	
	var panel = Ext.create('Ext.grid.Panel', {
		title: 'The names',
		store: Ext.data.StoreManager.lookup('nameStore'),
		columns: [
		    {text: 'ID', dataIndex: 'id'},
		    {text: 'Name', dataIndex: 'name'},
		],
		height: 200,
		width: 400,
		renderTo: Ext.getBody()
	});
});
