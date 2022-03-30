import React, { useEffect, useState } from "react";
import Description from "./Description";
import DonationForm from "./donations/DonationForm";
import Image from "./Image";
import SubHeadline from "./SubHeadline";
import Donation from "../model/Donation";
import Headline from "../Headline";
import { useParams } from "react-router-dom";
import { dataHandler } from "../../data/dataHandler";

const CausePage = () => {
  const params = useParams();

  // console.log(params.creatorLink);

  const [cause, setCause] = useState({});
  const [img, setImg] = useState();
  const [tips, setTips] = useState([]);

  useEffect(() => {
    const fetchCause = async () => {
      return await dataHandler.getCreatorDataByLink(params.creatorLink);
    };
    fetchCause().then((data) => {
      setCause(data);
    });
  }, [params.creatorLink]);

  useEffect(() => {
    const fetchImg = async () => {
      return await dataHandler.getImageForCard(cause.pageLink);
    };
    fetchImg().then((data) => {
      setImg(URL.createObjectURL(data));
    });
  }, [cause.pageLink]);

  useEffect(() => {
    const fetchTips = async () => {
      return await dataHandler.getTipsByCreatorLink(params.creatorLink);
    };
    fetchTips().then((data) => {
      setTips(data);
      console.log(data);
    });
  }, [params.creatorLink]);

  return (
    <div className="bg-gray-200 md:flex md:flex-col md:items-center md:flex-wrap">
      <div className=" px-4 md:w-8/12 ">
        <Headline title={cause.causeName} isTitle={true} />
        <Image
          style={"m-3 rounded-lg w-350 h-400 md:w-full md:h-650 md:pt-3 md:m-0"}
          jpgName={img}
        />
        <div className="fley flex-col md:flex md:flex-row">
          <Description description={cause.description} />
          <DonationForm />
        </div>
        { tips.length !== 0 ?
          <SubHeadline style={"text-3xl mb-4"} name={"Latest donators"} /> : ""
        }
        <div className="md:w-6/12">
          {tips.map((tip, index) => (
            <Donation
              donator={tip.supporter}
              amount={tip.amount}
              comment={tip.comment}
              time={tip.date}
            />
          ))}
        </div>
      </div>
    </div>
  );
};

export default CausePage;
