import { useState } from 'react';

const Join = () => {
    
    const [list,setList] = useState([]);
    const [inputs,setInputs] = useState({id:"",pw:"",name:""})
    const {id,pw,name} = inputs;

    const change = (e) =>{
        const {name, value} = e.target;
        setInputs({
            ...inputs,
            [name]:value,
        })
    }

    const join = () => {
        if(!id||!pw||!name){
            alert("입력안함");
            return;
        }
        const user = {id,pw,name};
        setList(list.concat(user));
        console.log(list);
        setInputs({
            id:"",
            pw:"",
            name:""
        })
    }

    return (
        <>
            <h2>회원가입</h2>
            <form>
                <p>
                    <input type="text" value={id} placeholder="Input Join id" onChange={change} name="id"/><br/>
                    <input type="password" value={pw} placeholder="Input Join pw" onChange={change} name="pw"/><br/>
                    <input type="text" value={name} placeholder="Input Join name" onChange={change} name="name"/><br/>
                    <input type="button" value="Join!" onClick={join}/>
                </p>
            </form>
            <ul>
                {
                    list.map((user)=>
                        <li>
                            {user.name}({user.id}) - {user.pw}
                        </li>
                    )
                }
            </ul>
        </>
    )
}
export default Join;