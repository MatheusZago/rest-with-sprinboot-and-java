package br.com.matheus.integrationTests.vo.pagedmodels;

import br.com.matheus.integrationTests.vo.BookVO;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement
public class PagedModelBook {

    @XmlElement(name = "content")
    private List<BookVO> content;

    public PagedModelBook() {
    }

    public List<BookVO> getContent() {
        return content;
    }

    public void setContent(List<BookVO> content) {
        this.content = content;
    }
}