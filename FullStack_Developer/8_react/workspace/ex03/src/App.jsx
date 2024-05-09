import { useState } from "react";
import "./App.css";

const List = ({movieList}) => {
  return(
    <>
      <h2>Movies</h2>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Genre</th>
            <th>Release Date</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {
            movieList.map((movie)=>{
              const {id,title,genre,date} = movie;
              return(
                <tr key={id}>
                  <td>{id}</td>
                  <td>{title}</td>
                  <td>{genre}</td>
                  <td>{date}</td>
                  <td><input type="button" value="Delete"/></td>
                </tr>
              )
            })
          }
        </tbody>
      </table>
    </>
  )
}
const Add = ({movieList,setMovieList}) => {
  const [inputs,setInputs] = useState({id:"",title:"",genre:"",date:""})
  const {id,title,genre,date} = inputs;
  const change = (e) =>{
    const {name, value} = e.target;
    setInputs({
        ...inputs,
        [name]:value,
    })
  }

  const add = () => {
    if(!id||!title||!genre||!date){
        alert("입력안함");
        return;
    }
    const movie = {id,title,genre,date};
    setMovieList(movieList.concat(movie));
    setInputs({
        id:"",
        title:"",
        genre:"",
        date:""
    })
  }
  return (
    <>
      <h2>Create Movie</h2>
      <form>
        <input type="text" placeholder="Input movie id" value={id} name="id" onChange={change}/><br/>
        <input type="text" placeholder="Input movie title" value={title} name="title" onChange={change}/><br/>
        <input type="text" placeholder="Input movie genre" value={genre} name="genre" onChange={change}/><br/>
        출시일 : <input type="date" value={date} name="date" onChange={change}/><br/>
        <input type="button" value="Add Movie" onClick={add}/>
      </form>
    </>
  )
}

function App() {
  const [movieList,setMovieList] = useState([
    {id:1,title:"Movie 1",genre:"Drama",date:"2022-01-01"},
    {id:2,title:"Movie 2",genre:"Action",date:"2022-01-02"},
    {id:3,title:"Movie 3",genre:"Comedy",date:"2022-01-03"},
  ]);
  const [flag,setFlag] = useState(false);
  const clickList = (e)=>{
    e.preventDefault();
    setFlag(false);
  }
  const clickAdd = (e)=>{
    e.preventDefault();
    setFlag(true);
  }
  return (
    <>
      <header>
        <a href="#" onClick={clickList}>List</a>
        <a href="#" onClick={clickAdd}>Add Niew Movie</a>
      </header>
      {
        flag?<Add movieList={movieList} setMovieList={setMovieList}></Add>:<List movieList={movieList}></List>
      }
    </>
  )
}

export default App;
