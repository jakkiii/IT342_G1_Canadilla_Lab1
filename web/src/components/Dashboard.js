import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import authService from '../services/authService';

function Dashboard() {
  const navigate = useNavigate();
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [showLogoutModal, setShowLogoutModal] = useState(false);

  useEffect(() => {
    fetchUserData();
  }, []);

  const fetchUserData = async () => {
    try {
      const userData = await authService.getCurrentUser();
      setUser(userData);
    } catch (err) {
      setError('Failed to load user data');
      if (err.response?.status === 401) {
        authService.logout();
        navigate('/login');
      }
    } finally {
      setLoading(false);
    }
  };

  const handleLogoutClick = () => {
    setShowLogoutModal(true);
  };

  const confirmLogout = () => {
    authService.logout();
    navigate('/login');
  };

  const cancelLogout = () => {
    setShowLogoutModal(false);
  };

  if (loading) {
    return (
      <div className="dashboard-container">
        <div className="loading">Loading user data...</div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="dashboard-container">
        <div className="error-message">{error}</div>
      </div>
    );
  }

  return (
    <div className="dashboard-container">
      <div className="dashboard-header">
        <h2>User Profile</h2>
        <button onClick={handleLogoutClick} className="logout-btn">
          Logout
        </button>
      </div>

      {user && (
        <div className="user-info">
          <div className="info-row">
            <span className="info-label">User ID:</span>
            <span className="info-value">{user.id}</span>
          </div>
          <div className="info-row">
            <span className="info-label">Username:</span>
            <span className="info-value">{user.username}</span>
          </div>
          <div className="info-row">
            <span className="info-label">Email:</span>
            <span className="info-value">{user.email}</span>
          </div>
          {user.firstName && (
            <div className="info-row">
              <span className="info-label">First Name:</span>
              <span className="info-value">{user.firstName}</span>
            </div>
          )}
          {user.lastName && (
            <div className="info-row">
              <span className="info-label">Last Name:</span>
              <span className="info-value">{user.lastName}</span>
            </div>
          )}
          {user.phoneNumber && (
            <div className="info-row">
              <span className="info-label">Phone:</span>
              <span className="info-value">{user.phoneNumber}</span>
            </div>
          )}
          <div className="info-row">
            <span className="info-label">Member Since:</span>
            <span className="info-value">
              {new Date(user.createdAt).toLocaleDateString()}
            </span>
          </div>
          <div className="info-row">
            <span className="info-label">Last Updated:</span>
            <span className="info-value">
              {new Date(user.updatedAt).toLocaleDateString()}
            </span>
          </div>
        </div>
      )}

      {showLogoutModal && (
        <div className="modal-overlay" onClick={cancelLogout}>
          <div className="modal-content" onClick={(e) => e.stopPropagation()}>
            <h3>Confirm Logout</h3>
            <p>Are you sure you want to logout?</p>
            <div className="modal-buttons">
              <button onClick={confirmLogout} className="btn-confirm">
                Yes, Logout
              </button>
              <button onClick={cancelLogout} className="btn-cancel">
                Cancel
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default Dashboard;
