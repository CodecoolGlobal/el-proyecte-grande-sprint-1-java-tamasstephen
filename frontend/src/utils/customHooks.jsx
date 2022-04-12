import React, { useRef, useEffect, useState } from "react";

export const usePageSectionReveal = (options) => {
  const wrapper = useRef(null);
  const [isVisible, setVisible] = useState("opacity-0");

  function showSection(entries) {
    const [entry] = entries;
    if (entry.isIntersecting) setVisible("opacity-100");
  }

  useEffect(() => {
    const observer = new IntersectionObserver(showSection, options);
    observer.observe(wrapper.current);
  });

  return [wrapper, isVisible];
};
