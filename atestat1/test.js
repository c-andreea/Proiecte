function check()
{
    var c=0;
    var q1 = document.quiz.question1.value;
    var q2 = document.quiz.question2.value;
    var q3 = document.quiz.question3.value;
    var q4 = document.quiz.question4.value;
    var q5 = document.quiz.question5.value;
    var q6 = document.quiz.question6.value;
    var q7 = document.quiz.question7.value;
    var q8 = document.quiz.question8.value;
    var q9 = document.quiz.question9.value;
    var result = document.getElementById('result');
    var quiz= document.getElementById("quiz");
    if(q1=="1")
    {c++;}
    if(q2=="2")
    {c++;}
    if(q3=="2")
    {c++;}
    if(q4=="1")
    {c++;}
    if(q5=="2")
    {c++;}
    if(q6=="1")
    {c++;}
    if(q7=="2")
    {c++;}
    if(q8=="1")
    {c++;}
    if(q9=="1")
    {c++;}
   quiz.style.display="none";
   result.textContent= `${c}`;
}