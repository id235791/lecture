import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import "./App.css";
const App = () => {
  const [movies, setMovies] = useState([
    { id: 1, title: 'Movie 1', genre: 'Drama', release_date: '2022-01-01' },
    { id: 2, title: 'Movie 2', genre: 'Action', release_date: '2022-02-01' },
    { id: 3, title: 'Movie 3', genre: 'Comedy', release_date: '2022-03-01' },
  ]);
  const [newMovie, setNewMovie] = useState({
    id: '',
    title: '',
    genre: '',
    release_date: '',
  });
  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setNewMovie({ ...newMovie, [name]: value });
  };
  const handleAddMovie = () => {
    if (!newMovie.id || !newMovie.title || !newMovie.genre || !newMovie.release_date) {
      alert('모든 입력값을 채워주세요.');
      return;
    }
    const isDuplicate = movies.some(movie => String(movie.id) === newMovie.id);
    if (isDuplicate) {
      alert('이미 존재하는 ID입니다.');
      setNewMovie({ ...newMovie, ['id']: '' });
      return;
    }
    setMovies([...movies, newMovie]);
    setNewMovie({ id: '', title: '', genre: '', release_date: '' });
  };
  const handleDeleteMovie = (id) => {
    const updatedMovies = movies.filter((movie) => movie.id !== id);
    setMovies(updatedMovies);
  };
  return (
    <Router>
      <div className='container'>
        <nav>
          <ul>
            <li>
              <Link to="/">List</Link>
            </li>
            <li>
              <Link to="/create">Add New Movie</Link>
            </li>
          </ul>
        </nav>
        <Routes>
          <Route path="/" element={<Home movies={movies} onDeleteMovie={handleDeleteMovie} />} />
          <Route path="/create" element={<CreateMovie newMovie={newMovie} onInputChange={handleInputChange} onAddMovie={handleAddMovie} />} />
        </Routes>
      </div>
    </Router>
  );
};
const Home = (props) => {
  const { movies, onDeleteMovie } = props;
  return (
    <div>
      <h1>Movies</h1>
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
          {movies.map((movie) => (
            <tr key={movie.id}>
              <td>{movie.id}</td>
              <td>{movie.title}</td>
              <td>{movie.genre}</td>
              <td>{movie.release_date}</td>
              <td>
                <button onClick={() => onDeleteMovie(movie.id)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

const CreateMovie = (props) => {
  const { newMovie, onInputChange, onAddMovie } = props;
  return (
    <div>
      <h1>Create Movie</h1>
      <form>
        <div>
          <input
            type="number"
            name="id"
            value={newMovie.id}
            onChange={onInputChange}
            placeholder="Input movie id"
          />
        </div>
        <div>
          <input
            type="text"
            name="title"
            value={newMovie.title}
            onChange={onInputChange}
            placeholder="Input movie title"
          />
        </div>
        <div>
          <input
            type="text"
            name="genre"
            value={newMovie.genre}
            onChange={onInputChange}
            placeholder="Input movie genre"
          />
        </div>
        <div>
          <label>출시일 : </label>
          <input
            type="date"
            name="release_date"
            value={newMovie.release_date}
            onChange={onInputChange}
          />
        </div>
      </form>
      <button onClick={onAddMovie}>Add Movie</button>
    </div>
  );
};

export default App;