const Pagination = ({pageMaker})=>{
    const startPage = pageMaker.startPage;
    const endPage = pageMaker.endPage;
    const cri = pageMaker.cri;
    const pagenum = cri.pagenum;
    const el = [];
    for(let i=startPage;i<=endPage;i++){
        if(pagenum == i){
            el.push(<span className="nowPage" key={i}>{i}</span>);
        }
        else{
            el.push(<a className="btn changePage" href={i} key={i}>{i}</a>);
        }
    }
    return (<div className="pagination w1000 tac">
        {
            pageMaker.prev?
            <a className="btn changePage" href={startPage - 1} key={startPage - 1}>&lt;</a>
            :""
        }
        {el}
        {
            pageMaker.next?
            <a className="btn changePage" href={endPage + 1} key={endPage + 1}>&gt;</a>
            :""
        }
    </div>);
}
export default Pagination;