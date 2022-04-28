import { data } from "autoprefixer";
import React, { useEffect, useState } from "react";
import { dataHandler } from "../data/dataHandler";
import User from "./model/User";
import Headline from "./Headline";

const UserPage = () => {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    const fetchUsers = async () => {
      return await dataHandler.getAllUser();
    };
    fetchUsers().then((data) => {
      setUsers(data.customers);
    });
  }, []);

  return (
    <div className="px-4 md:px-0 md:flex md:flex-col md:items-center pt-8 md:pt-16">
      <div className="md:w-8/12 md:max-w-3xl">
        <Headline isTitle={true} title={"Users"}  />
      <ul className="pt-4" >
        {users.map((user, index) => (
          <User key={index} user={user} />
        ))}
      </ul>
    </div>
    </div>
    
  );
};

export default UserPage;
