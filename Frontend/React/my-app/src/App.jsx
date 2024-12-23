import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css';
import Login from './pages/Login';
import Register from './pages/Registration';
import NewsFeed from './pages/News-feed';
import ProfilePage from './pages/ProfilePage';
import PendingRequests from './pages/Pending Requests';
import FriendsList from './pages/FriendsList';
function App() {
  // Set a test userId in localStorage if not already set
 // if (!localStorage.getItem("userId")) {
    //localStorage.setItem("userId", 1001);
 // }

  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/newsfeed" element={<NewsFeed />} />
        <Route path="/profile/:userId" element={<ProfilePage/>} />
        <Route path="/friends-list" element={<FriendsList />} />
        <Route path="/notifications" element={<PendingRequests />} />
        
      </Routes>
    </Router>
  );
}

export default App;
