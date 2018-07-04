    function alertFoo(){
        alert("you clicked the button");

        }

        function bar(){
        console.log("Bar invoked");
        var counter = 3;
        counter++;

        console.log(counter);

        counter = "Three";

        console.log(counter);
        return counter;
        }

    function alertText(){
    alert("Wow, don't touch that text, man!")
    }

    function guitarChange(){
            document.getElementById("guitar").innerHTML = "bird"; //change content of a text
        }

    function toggleLorem(){
        $("#lorem").toggle();
    }

    function toggleBodyParagraphs(){
        $(".bodyParagraph").toggle();
    }

