import { dataHandler } from "../data/dataHandler";

export function shortenDescription(description) {
  return description.length <= 80
    ? description
    : description.substring(0, 80) + "...";
}

export const fetchImages = async (listOfCauses, stateHandler) => {
  const newCauses = [];
  for (const cause of listOfCauses) {
    const cardImageBlob = await dataHandler.getImageForCard(cause.pageLink);
    const imageUrl = URL.createObjectURL(cardImageBlob);
    const myCause = { ...cause };
    myCause["imageLink"] = imageUrl;
    newCauses.push(myCause);
  }
  stateHandler(() => [...newCauses]);
};

