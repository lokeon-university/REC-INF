import requests
import re
import os
import queue
from urllib.parse import urlparse, unquote


class Crawler(object):
    def __init__(self, seed):
        self.routeFolder = 'html/'
        self.seed = seed
        self.queue = queue.Queue()
        self.visitedURL = set()

    def get_html(self, url):
        try:
            html = requests.get(url)
        except Exception as ex:
            print(ex)
            return ""

        return html.text

    def filter_links(self, word):

        esp_wiki = 'es.wikipedia.org/wiki/' in word
        sharp = '#' not in word
        qst = '?' not in word
        ctg = 'Categoría:' not in word
        arc = 'Archivo:' not in word
        ayu = 'Ayuda:' not in word
        user = 'Usuario:' not in word
        spc = 'Especial:' not in word
        wk = 'Wikipedia:' not in word
        anx = 'Anexo:' not in word
        ptl = 'Portal:' not in word
        dcs = 'Discusión:' not in word
        wkp = 'Wikiproyecto:' not in word

        if esp_wiki and sharp and qst and ctg and arc and ayu and user and spc and wk and anx and ptl and dcs and wkp:
            return True
        else:
            return False

    def get_links(self, url):
        html = self.get_html(url)
        # TILDES
        text = unquote(html)
        parsed = urlparse(url)
        # <scheme>://<netloc>/<path>;<params>?<query>#<fragment>
        # f is for straightfoward values
        base = f"{parsed.scheme}://{parsed.netloc}"
        links = re.findall('''<a\\s+(?:[^>]*?\\s+)?href="([^"]*)"''', text)
        for i, link in enumerate(links):  # entire link
            if not urlparse(link).netloc:
                link_with_base = base + link
                links[i] = link_with_base

        return set(filter(self.filter_links, links))

    def extract_info(self, url):
        html = self.get_html(url)
        f = open(self.routeFolder +
                 os.path.basename(urlparse(url).path)+".html", 'w').write(html)

    def crawl(self, url):
        for link in self.get_links(url):
            if link not in self.visitedURL:
                print(link)
                self.queue.put(link)
                self.visitedURL.add(link)
                self.extract_info(link)

        self.crawl(self.queue.get(0))

    def start(self):
        self.crawl(self.seed)


if __name__ == "__main__":
    crawler = Crawler("https://es.wikipedia.org/wiki/Mamihlapinatapai")
    crawler.start()
