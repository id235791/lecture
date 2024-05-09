import Button from "./Button";
import Postcode from "./DaumPostCode";

const Join = ({checkId,pwCheck,hobbyKeyUp,addHobby,clickJoin}) => {
    return (
        <>
            <div id='wrap' className='join'>
                <form name="joinForm" method="post">
                    <table>
                        <tbody>
                            <tr>
                                <td id="result" colSpan={2}></td>
                            </tr>
                            <tr className="userid_area">
                                <th>아이디</th>
                                <td>
                                    <input type="text" name="userid" className="input-with-btn"/>
                                    <Button text="중복검사" size="small" onClick={checkId}/>
                                </td>
                            </tr>
                            <tr className="userpw_area">
                                <th>비밀번호</th>
                                <td>
                                    <input type="password" name="userpw" onChange={pwCheck}/>
                                </td>
                            </tr>
                            <tr className="userpw_re_area" >
                                <th>비밀번호 확인</th>
                                <td>
                                    <input type="password" name="userpw_re" onChange={pwCheck}/>
                                </td>
                            </tr>
                            <tr>
                                <td colSpan={2}>
                                    <div className="pw_check">
                                        <span>영어 대문자, 소문자, 숫자, 특수문자(~,?,!,@,-)를 모두 하나 이상 포함해야 해요 😃</span>
                                        <span>최소 8자 이상의 비밀번호가 보안에 안전해요 😄</span>
                                        <span>같은 문자가 연속해서 사용되지 않았어요 😆</span>
                                        <span>사용할 수 없는 문자가 포함되지 않았어요 🙂</span>
                                        <span>비밀번호 확인이 완료되었어요! 😊</span>
                                    </div>                                
                                </td>
                            </tr>
                            <tr className="username_area">
                                <th>이름</th>
                                <td>
                                    <input type="text" name="username"/>
                                </td>
                            </tr>
                            <tr className="gender_area radio_area">
                                <th>성별</th>
                                <td>
                                    <div>
                                        <ul>
                                            <li className="radio_item">
                                                <input type="radio" name="usergender" id="usergender1" value='M'/>
                                                <label htmlFor="usergender1">남자</label>
                                            </li>
                                            <li className="radio_item">
                                                <input type="radio" name="usergender" id="usergender2" value='W'/>
                                                <label htmlFor="usergender2">여자</label>
                                            </li>
                                        </ul>
                                        <ul>
                                            <li className="radio_item">
                                                <input type="radio" name="foreigner" id="foreigner1" value='K'/>
                                                <label htmlFor="foreigner1">내국인</label>
                                            </li>
                                            <li className="radio_item">
                                                <input type="radio" name="foreigner" id="foreigner2" value='F'/>
                                                <label htmlFor="foreigner2">외국인</label>
                                            </li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                            <tr className="zipcode_area">
                                <th>우편번호</th>
                                <td>
                                    <input className="input-with-btn" name="zipcode" type="text" id="zipcode" placeholder="우편번호" readOnly onClick={()=>{document.getElementById("post_btn").click()}}/>
                                    <Postcode></Postcode>
                                </td>
                            </tr>
                            <tr className="addr_area">
                                <th>주소</th>
                                <td>
                                    <input readOnly name="addr" type="text" id="addr" placeholder="주소"/>
                                </td>
                            </tr>
                            <tr className="addrdetail_area">
                                <th>상세주소</th>
                                <td>
                                    <input name="addrdetail" type="text" id="addrdetail" placeholder="상세주소"/>
                                </td>
                            </tr>
                            <tr className="addretc_area">
                                <th>참고항목</th>
                                <td>
                                    <input readOnly name="addretc" type="text" id="addretc" placeholder="참고항목"/>
                                </td>
                            </tr>
                            <tr className="hobby_area">
                                <th id="hobby_lbl">취미</th>
                                <td>
                                    <div>
                                        <div className="hobby_input">
                                            <input className="input-with-btn" type="text" name="hobby" onKeyUp={hobbyKeyUp}/><Button onClick={addHobby} text="추가" size="small"/>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <div className="hobby_list"></div>
                                    <input type="hidden" value="" name="userhobby"/>
                                </td>
                            </tr>
                            
                            <tr>
                                <td colSpan={2}>
                                    <Button className='submit' text="가입 완료" size="half" onClick={clickJoin}/>
                                    <Button className='back' text="이전으로" size="half" onClick={(e)=>{e.preventDefault();document.location.replace("/");}}/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </>
    )
}
export default Join;