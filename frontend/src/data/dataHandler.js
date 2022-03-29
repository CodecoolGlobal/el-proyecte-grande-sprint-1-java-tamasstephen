export const dataHandler = {
  async getHighlights() {
    return this.getApi("highlighted");
  },

  async getImageForCard(causeLink) {
    return await this.getImage(`creator/${causeLink}/image`);
  },

  async registerUser(payload) {
    return await this.postTextJson("user", payload);
  },

  async logout() {
    return await this.getApi("logout");
  },

  async getApi(endpoint) {
    const result = await fetch(`http://localhost:8080/${endpoint}`);
    return await result.json();
  },

  async postMultiPartForm(formData) {
    const result = await fetch("http://localhost:8080/creator-profile", {
      method: "POST",
      body: formData,
    });
    return await result.json();
  },

  async postTextJson(endpoint, payload) {
    const result = await fetch(`http://localhost:8080/${endpoint}`, {
      headers: { "Content-Type": "application/json" },
      method: "POST",
      body: JSON.stringify(payload),
    });
    return await result.json();
  },

  async getImage(endpoint) {
    const result = await fetch(`http://localhost:8080/${endpoint}`);
    return await result.blob();
  },
};
