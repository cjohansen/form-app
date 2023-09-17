# An example of a stateless, data-driven frontend

This repo contains my implementation of the code from Abhinav Omprakash's
[interesting article about what makes frontend development
tricky](https://www.abhinavomprakash.com/posts/what-makes-frontend-development-tricky/).
I also wrote [a follow-up
article](https://cjohansen.no/stateless-data-driven-uis/) detailing the thought
process behind this code.

This code can also be seen as a more detailed practical demonstration of my
talk about [Stateless, data-driven UIs](https://vimeo.com/861600197).

## Overview

This sample app uses [dumdom](https://github.com/cjohansen/dumdom) as a
rendering library. `dumdom` is a component-based virtual DOM library, much like
reagent, with two important differences: it does not use React, and it does not
allow component-local state. There are no other libraries. State management is
implemented with an atom and a single `add-watch`. Most of the code is plain and
pure ClojureScript functions.

### Starting the app

The app is started from [`form-app.dev`](./dev/form_app/dev.cljs). It defines a
global store for all the application data, finds the element to render the app
to, and passes those to the `start` function defined in
[`form-app.core`](./src/form_app/core.cljs). The `start` function does three
things:

1. Register a global event handler in dumdom that performs keyword dispatch for
   named "actions"
2. Add a watcher for the global application store that re-renders the app on
   every change
3. Perform the initial render

### Rendering

To render the app, the current snapshot of the application store (called
"state") is passed to the `prepare-ui-data` function. This function converts
business domain data to generic UI data. The generic UI data is then passed to
the generic UI components for rendering.

In this model the domain data to UI data conversion is run for every change, but
virtual DOM computation will be short circuited for unchanged values, and the
DOM will only update when there are actual changes. Importantly, even event
handler attributes will not cause re-renders since they are not functions.

In other words, there is always some unnecessary work in the data conversion,
but that step is fast and generally irrelevant. This model scales: I've used it
in large applications for 10 years.

## Where's the event bus?

If you're looking at this code after watching my JavaZone talk, you might be
wondering where the event bus went. The purpose of the event bus is to loosely
couple actions from the components that initiate them, and avoid circular
dependencies. The global event handler in dumdom serves the same purpose, and
thus a dedicated event bus is not necessary.
