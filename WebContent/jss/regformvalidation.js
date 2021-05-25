 function validation()
 
{
var name=document.getElementById("name").value;
 var regname=/^[a-z A-Z ]{3,30}$/;
  if(name.match(regname))
 {

}
else
{
alert("only alphabets are allowed");
return false;
 }
 
 var year=document.getElementById("year").value;
 var regyear=/^[1-3]{1,1}$/;
if(year.match(regyear))
{
}
else
{
alert("only numerical value between 1 to 3 are allowed")
return false;
}
 
var mobile1=document.getElementById("mbi").value;
 var regmbi=/^([0|\+[0-9]{1,5})?([7-9][0-9]{9})$/;
  if(mobile1.match(regmbi))
 {
document.getElementById("msg3").innerHTML="";
}
else
{
alert("enter valid mobile no");
return false;
 }

var name=document.getElementById("u_name").value;

 var reguname=/[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/;
  if(name.match(reguname))
 {

}
else
{
alert("enter valid email id");
return false;
 }
var password=document.getElementById("pass").value;
var regpass = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%&]){8,16}/;

if(password.match(regpass))
{

}
else
{
alert("password must contain one capital latter one small one spesial caharecter min size at list 8 and max is 16");
return false;
}
var file=document.getElementById("fileToUpload").value;
  if (file == "") 
  {
            alert("Browse to upload a valid File with sutable extension");
            return false;
        }
        else if (file.split(".")[1].toUpperCase() == "PNG" ||file.split(".")[1].toUpperCase() == "JPEG" || file.split(".")[1].toUpperCase() == "JPG")
            return true;
        else 
		{
            alert("File with " + file.split(".")[1] + " is invalid. Upload a validfile with png.jpg,jpeg extensions");
            return false;
        }
        return true;
    

 }