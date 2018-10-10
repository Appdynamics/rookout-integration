import tornado.ioloop, tornado.web
import argparse,configparser
import sys, getopt
import json





class RookIntegration(object) :
    config = configparser.ConfigParser()

    def __init__(self, command_args=None):
        if command_args:
            self.command_args = command_args
        else:
            parser = argparse.ArgumentParser()
            parser.add_argument("--configFile", "-c", required=True, default="config.properties",
                                help="config file is needed")

        self.command_args = parser.parse_args()
        self.config.read(self.command_args.configFile)


class MainHandler(tornado.web.RequestHandler):
    def get(self):
        self.write("Hello, world")



def make_app():
    return  tornado.web.Application([(r"/", MainHandler),])

if __name__ == "__main__":
    integration = RookIntegration()
    ws_port = integration.config.get("ports","WebServerPort")

    app = make_app()
    app.listen(int(ws_port))
    tornado.ioloop.IOLoop.current().start()