package com.yc.mapgoogle;


import java.util.List;

public class AddressQueryResponse {
    private String status;
    private List<AddressQueryResponse.PredictionsBean> predictions;

    public AddressQueryResponse() {
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<AddressQueryResponse.PredictionsBean> getPredictions() {
        return this.predictions;
    }

    public void setPredictions(List<AddressQueryResponse.PredictionsBean> predictions) {
        this.predictions = predictions;
    }

    public static class PredictionsBean {
        private String description;
        private String id;
        private String place_id;
        private String reference;
        private AddressQueryResponse.PredictionsBean.StructuredFormattingBean structured_formatting;
        private List<AddressQueryResponse.PredictionsBean.MatchedSubstringsBean> matched_substrings;
        private List<AddressQueryResponse.PredictionsBean.TermsBean> terms;
        private List<String> types;

        public PredictionsBean() {
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPlace_id() {
            return this.place_id;
        }

        public void setPlace_id(String place_id) {
            this.place_id = place_id;
        }

        public String getReference() {
            return this.reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }

        public AddressQueryResponse.PredictionsBean.StructuredFormattingBean getStructured_formatting() {
            return this.structured_formatting;
        }

        public void setStructured_formatting(AddressQueryResponse.PredictionsBean.StructuredFormattingBean structured_formatting) {
            this.structured_formatting = structured_formatting;
        }

        public List<AddressQueryResponse.PredictionsBean.MatchedSubstringsBean> getMatched_substrings() {
            return this.matched_substrings;
        }

        public void setMatched_substrings(List<AddressQueryResponse.PredictionsBean.MatchedSubstringsBean> matched_substrings) {
            this.matched_substrings = matched_substrings;
        }

        public List<AddressQueryResponse.PredictionsBean.TermsBean> getTerms() {
            return this.terms;
        }

        public void setTerms(List<AddressQueryResponse.PredictionsBean.TermsBean> terms) {
            this.terms = terms;
        }

        public List<String> getTypes() {
            return this.types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        public static class TermsBean {
            private int offset;
            private String value;

            public TermsBean() {
            }

            public int getOffset() {
                return this.offset;
            }

            public void setOffset(int offset) {
                this.offset = offset;
            }

            public String getValue() {
                return this.value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class MatchedSubstringsBean {
            private int length;
            private int offset;

            public MatchedSubstringsBean() {
            }

            public int getLength() {
                return this.length;
            }

            public void setLength(int length) {
                this.length = length;
            }

            public int getOffset() {
                return this.offset;
            }

            public void setOffset(int offset) {
                this.offset = offset;
            }
        }

        public static class StructuredFormattingBean {
            private String main_text;
            private String secondary_text;
            private List<AddressQueryResponse.PredictionsBean.StructuredFormattingBean.MainTextMatchedSubstringsBean> main_text_matched_substrings;
            private List<AddressQueryResponse.PredictionsBean.StructuredFormattingBean.SecondaryTextMatchedSubstringsBean> secondary_text_matched_substrings;

            public StructuredFormattingBean() {
            }

            public String getMain_text() {
                return this.main_text;
            }

            public void setMain_text(String main_text) {
                this.main_text = main_text;
            }

            public String getSecondary_text() {
                return this.secondary_text;
            }

            public void setSecondary_text(String secondary_text) {
                this.secondary_text = secondary_text;
            }

            public List<AddressQueryResponse.PredictionsBean.StructuredFormattingBean.MainTextMatchedSubstringsBean> getMain_text_matched_substrings() {
                return this.main_text_matched_substrings;
            }

            public void setMain_text_matched_substrings(List<AddressQueryResponse.PredictionsBean.StructuredFormattingBean.MainTextMatchedSubstringsBean> main_text_matched_substrings) {
                this.main_text_matched_substrings = main_text_matched_substrings;
            }

            public List<AddressQueryResponse.PredictionsBean.StructuredFormattingBean.SecondaryTextMatchedSubstringsBean> getSecondary_text_matched_substrings() {
                return this.secondary_text_matched_substrings;
            }

            public void setSecondary_text_matched_substrings(List<AddressQueryResponse.PredictionsBean.StructuredFormattingBean.SecondaryTextMatchedSubstringsBean> secondary_text_matched_substrings) {
                this.secondary_text_matched_substrings = secondary_text_matched_substrings;
            }

            public static class SecondaryTextMatchedSubstringsBean {
                private int length;
                private int offset;

                public SecondaryTextMatchedSubstringsBean() {
                }

                public int getLength() {
                    return this.length;
                }

                public void setLength(int length) {
                    this.length = length;
                }

                public int getOffset() {
                    return this.offset;
                }

                public void setOffset(int offset) {
                    this.offset = offset;
                }
            }

            public static class MainTextMatchedSubstringsBean {
                private int length;
                private int offset;

                public MainTextMatchedSubstringsBean() {
                }

                public int getLength() {
                    return this.length;
                }

                public void setLength(int length) {
                    this.length = length;
                }

                public int getOffset() {
                    return this.offset;
                }

                public void setOffset(int offset) {
                    this.offset = offset;
                }
            }
        }
    }
}

