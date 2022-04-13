import React, { useEffect, useState } from "react";
import Description from "./Description";
import DonationForm from "./donations/DonationForm";
import Image from "./Image";
import SubHeadline from "./SubHeadline";
import Donation from "../model/Donation";
import Headline from "../Headline";
import { useParams } from "react-router-dom";
import { dataHandler } from "../../data/dataHandler";
import Donations from "./donations/Donations";

const CausePage = () => {
  const params = useParams();
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
    });
  }, [params.creatorLink]);

  return (
    <div className="bg-slate-100 md:flex md:flex-col md:items-center md:flex-wrap">
      <div className=" px-4 md:w-8/12  max-w-6xl">
        <div className="py-4 lg:pt-16 lg:pb-8">
          <Headline title={cause.causeName} isTitle={true} />
        </div>
        <Image
          style={
            "rounded-lg w-350 h-400 md:w-full md:h-650 md:m-0 aspect-video object-cover object-center"
          }
          jpgName={img}
        />
        <div className="flex flex-col lg:flex lg:flex-row  pt-4 md:pt-8">
          <div className=" lg:w-8/12 pr-4 pb-4">
            <SubHeadline
              style={
                "text-3xl py-4 text-slate-500 font-medium tracking-tighter"
              }
              name={"Description"}
            />
            <Description description={cause.description} />
          </div>
          <DonationForm />
        </div>
        {tips.length !== 0 ? (
          <SubHeadline
            style={
              "text-3xl mb-4  py-4  text-slate-500 font-medium tracking-tighter"
            }
            name={"Latest donators"}
          />
        ) : (
          ""
        )}
        <div className="md:w-6/12 pb-20">
          <Donations items={tips} />
        </div>
      </div>
    </div>
  );
};

export default CausePage;
