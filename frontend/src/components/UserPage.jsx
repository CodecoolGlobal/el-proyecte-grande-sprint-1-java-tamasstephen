import { data } from 'autoprefixer';
import React, { useEffect, useState } from 'react'
import { dataHandler } from '../data/dataHandler';
import User from './model/User';

const UserPage = () => {
  const [users, setUsers] = useState({});

  useEffect(() => {
    const fetchUsers = async () => {
      return await dataHandler.getAllUser();
    };
    fetchUsers().then((data) => {
      console.log(data);
      setUsers(data);
    })
  }, []);


  return (
    <div>
      {users.map((user, index) => (
        <User key={index} user={user}/>
      ))}
    </div>
  )
}

export default UserPage