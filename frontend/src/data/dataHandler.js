export const dataHandler = {
  async getHighlights() {
    return this.getApi("highlighted");
  },

  async getApi(endpoint) {
    const result = await fetch(`http://localhost:8080/${endpoint}`);
    return await result.json();
  },
};
