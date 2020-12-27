import World

def main():
    # pick the right action
    agent = World.player
    maxAct, maxVal = max_Q(agent)
    (agent, a, r, agent_updated) = do_action(max_act)

    # Update Q
    maxAct, maxVal = max_Q(agent_updated)

    # Start game
    World.start_game()
    print('Success! Score: ', maxVal)
