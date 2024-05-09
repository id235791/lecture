const Button = ({href="", className="",text,size="common",hide,onClick,id}) =>{
    return <a
        onClick={onClick}
        href={href}
        className={`${size}-btn ${className}`}
        // style={ {속성명:'속성값'}    }
        style={hide?{dispaly:'none'}:{}}
        id={id}>
        {text}
    </a>
}
export default Button;