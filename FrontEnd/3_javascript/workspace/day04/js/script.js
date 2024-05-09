function func1(){
    console.log('func1 함수 호출!');
}

function func2(num){
    console.log(`전달받은 매개변수의 값 : ${num}`);
}

function func3(start, end){
    let sum = 0;
    for(let i=start; i<=end; i++){
        sum += i;
    }
    console.log(`${start}부터 ${end}까지의 총합은 ${sum}입니다.`);
}

function func4(name='무명'){
    console.log(`강아지의 이름은 ${name}입니다.`);
}

function func5(num1, ...num2){
    console.log(`num1의 값 : ${num1}`);
    console.log(`num2의 값 : ${num2}`);   
}

function func6(...jumsu){
    for(let i of jumsu){
        console.log(i);
    }
}
