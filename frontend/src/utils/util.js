export function shortenDescription(description) {
  return description.length <= 80
    ? description
    : description.substring(0, 80) + "...";
}
