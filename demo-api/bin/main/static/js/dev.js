if (!String.prototype.startsWith) {
    String.prototype.startsWith = function(searchString, position){
      position = position || 0;
      return this.substr(position, searchString.length) === searchString;
  };
}


var isloading = true;

$(document).on({
	ajaxStart: function() {
		if (isloading) {
			$('body').addClass('loading');    
		}
	},
	ajaxStop: function() { 
		$('body').removeClass('loading'); 
	}    
});	

var $q = new function () {
	var req = {
		    url		: '../' + t + '/rest/',
		    dataType: 'json',
		    method	: 'POST',
		    data	: ''
		};
	
    this.post = function (data) {
        req.data = data || '';
        
        if (req.data.direction == false) {
        	isloading = false;
        } else {
        	isloading = true;
        }
        
        return $.ajax(req);
    }
}