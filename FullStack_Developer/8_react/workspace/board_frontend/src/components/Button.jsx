const Button = (props) => {
    return <input className={props.className} type="button" id={props.id} value={props.value} onClick={props.onClick}></input>
}
export default Button;