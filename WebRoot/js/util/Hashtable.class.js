/*
 * 构造一个简单的类似Hashtable对象
 * add by DJ
 */
function Hashtable(){
	this._hash = new Object();
	this._keyAry = new Array();
	this._valAry = new Array();
}
Hashtable.prototype.put = 
	function(key,value){
		if(typeof(key)!="undefined"){
			if(this.contains(key) == false){
				this._hash[key] = typeof(value)=="undefined" ? null:value;
				this._keyAry.push(key);
				this._valAry.push([key,value]);
				return true;
			}else{
				this._hash[key] = typeof(value)=="undefined" ? null:value;
				for(var i=0;i<this._valAry.length;i++){
					var val = this._valAry[i];
					if(val[0] == key){
						this._valAry[i] = [key,value];
					}
				}
				return true;
			}
		}else{
			return false;
		}
	}
Hashtable.prototype.remove = 
	function(key){
		if(key != null && typeof(key)!="undefined"){
			delete this._hash[key];
			var newK = new Array();
			var newV = new Array();
			for(var i=0;i<this._keyAry.length;i++){
				var k = this._keyAry[i];
				var v = this._valAry[i];
				if(key != k){
					newK.push(k);
					newV.push(v);
				}
			}
			this._keyAry = newK;
			this._valAry = newV;
		}		
	}
Hashtable.prototype.count = function(){return this._keyAry.length;}
Hashtable.prototype.get = function(key){return this._hash[key];}
Hashtable.prototype.contains = function(key){ return typeof(this._hash[key])!="undefined";}
Hashtable.prototype.getKeys = function(){return this._keyAry;}
Hashtable.prototype.getValues = function(){var vals = new Array();for(var i=0;i<this._valAry.length;i++){var val=this._valAry[i];vals.push(val[1]);} return vals;}

