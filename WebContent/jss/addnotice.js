  function changeyear()
  {
      
  var year1=document.getElementById("year1");
  year1.disabled=true;
   var course=document.getElementById("course1").value;
   var i;
    for(i = year1.options.length - 1 ; i >= 0 ; i--)
    {
        year1.remove(i);
    }
 
  
  var result=course.localeCompare("MCA");
  var result1=course.localeCompare("Student");
   var a= document.createElement("option");
    a.text = "1st year";
    a.value=1;
	var b= document.createElement("option");
    b.text = "2nd year";
    b.value=2;
	var c= document.createElement("option");
    c.text = "3rd year";
    c.value=3;
  if(result==0)
  {
      year1.disabled=false;
  year1.options.add(a);
  year1.options.add(b);
  year1.options.add(c);
  }
  else if(result1==0)
  {
      
      year1.disabled=true;
  }
  else
  {
      year1.disabled=false;
   year1.options.add(a);
  year1.options.add(b);
  }
  }


