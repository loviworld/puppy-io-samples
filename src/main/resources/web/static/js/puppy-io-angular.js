var app = angular.module('puppy-io', []);

app.provider('WebServer', function () {
	this.config = function (appName,port) {
	  this.configuration = {'appName':appName,'port':port};
	};
	this.$get = function () {
	  return this;
	};
});

app.directive('uiRepeat', function($rootScope,WebServer,WebServer) {
    return {
        transclude: 'element',
        compile: function(element, attr, linker) {
            
            return function($scope, $element, $attr) {
                var myLoop = $attr.uiRepeat,
                    match = myLoop.match(/^\s*(.+)\s+in\s+(.*?)\s*(\s+track\s+by\s+(.+)\s*)?$/),
                    listenerAddress = match[2],
                    indexString = match[1],
                    parent = $element.parent(),
                    elements = [];
                
               var sl = new ServiceListener(WebServer.configuration.appName, WebServer.configuration.port);
               sl.onopen(function() {
            	   sl.listen(listenerAddress, function(error, message) {
            		   var collection = JSON.parse(message.body);
           				
            		   var i, block, childScope;
                       
                       if (elements.length > 0) {
                           for (i = 0; i < elements.length; i++) {
                               elements[i].el.remove();
                               elements[i].scope.$destroy();
                           };
                           elements = [];
                       }

                       for (i = 0; i < collection.length; i++) {
                           childScope = $scope.$new();
                           
                           childScope[indexString] = collection[i];
                          
                           linker(childScope, function(clone) {
                        	   
                               parent.append(clone);
                               block = {};
                               block.el = clone;
                               block.scope = childScope;
                               elements.push(block);
                           });
                       };
                       
                       $scope.$apply();
            		   
           			});
           		});
	               
            }
        }
    }
});

app.directive('ui', function($rootScope,WebServer,WebServer) {
    return {
        transclude: 'element',
        compile: function(element, attr, linker) {
            
            return function($scope, $element, $attr) {
                var myLoop = $attr.ui,
                    match = myLoop.match(/^\s*(.+)\s+in\s+(.*?)\s*(\s+track\s+by\s+(.+)\s*)?$/),
                    listenerAddress = match[2],
                    indexString = match[1],
                    parent = $element.parent(),
                    elements = [];
                
               var sl = new ServiceListener(WebServer.configuration.appName, WebServer.configuration.port);
               sl.onopen(function() {
            	   sl.listen(listenerAddress, function(error, message) {
            		   var result = JSON.parse(message.body);
           				
            		   var i, block, childScope;
                       
                       if (elements.length > 0) {
                           for (i = 0; i < elements.length; i++) {
                               elements[i].el.remove();
                               elements[i].scope.$destroy();
                           };
                           elements = [];
                       }

                       childScope = $scope.$new();
                       
                       childScope[indexString] = result;
                      
                       linker(childScope, function(clone) {
                    	   
                           parent.append(clone);
                           block = {};
                           block.el = clone;
                           block.scope = childScope;
                           elements.push(block);
                       });
                       
                       $scope.$apply();
            		   
           			});
           		});
	               
            }
        }
    }
});
